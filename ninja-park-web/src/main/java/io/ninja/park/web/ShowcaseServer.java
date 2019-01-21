/**
 * 
 */
package io.ninja.park.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.tomcat.util.scan.StandardJarScanner;
import org.eclipse.jetty.apache.jsp.JettyJasperInitializer;
import org.eclipse.jetty.jsp.JettyJspServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.webapp.Configuration;

/**
 * @author 
 *
 */
public class ShowcaseServer {
	public static final int PORT = 8080;
	public static final String CONTEXT = "/showcase";
	public static final String[] TLD_JAR_NAMES = new String[] { "sitemesh", "spring-webmvc", "shiro-web" };

	// Resource path pointing to where the WEBROOT is
		private static final String WEBROOT_INDEX = "src/main/webapp/";

		/**
		 * JspStarter for embedded ServletContextHandlers
		 * 
		 * This is added as a bean that is a jetty LifeCycle on the
		 * ServletContextHandler. This bean's doStart method will be called as the
		 * ServletContextHandler starts, and will call the ServletContainerInitializer
		 * for the jsp engine.
		 *
		 */
		public static class JspStarter extends AbstractLifeCycle
				implements ServletContextHandler.ServletContainerInitializerCaller {
			JettyJasperInitializer sci;
			ServletContextHandler context;

			public JspStarter(ServletContextHandler context) {
				this.sci = new JettyJasperInitializer();
				this.context = context;
				this.context.setAttribute("org.apache.tomcat.JarScanner", new StandardJarScanner());
			}

			@Override
			protected void doStart() throws Exception {
				ClassLoader old = Thread.currentThread().getContextClassLoader();
				Thread.currentThread().setContextClassLoader(context.getClassLoader());
				try {
					sci.onStartup(null, context.getServletContext());
					super.doStart();
				} finally {
					Thread.currentThread().setContextClassLoader(old);
				}
			}
		}

		public static void main(String[] args) throws Exception {
			int port = 8080;

			ShowcaseServer main = new ShowcaseServer(port);
			main.start();
			main.waitForInterrupt();
		}

		private static final Logger LOG = Logger.getLogger(ShowcaseServer.class.getName());

		private int port;
		private Server server;

		public ShowcaseServer(int port) {
			this.port = port;
		}

		public void start() throws Exception {
			server = new Server();

			// Define ServerConnector
			ServerConnector connector = new ServerConnector(server);
			connector.setPort(port);
			server.addConnector(connector);

			// Add annotation scanning (for WebAppContexts)
			Configuration.ClassList classlist = Configuration.ClassList.setServerDefault(server);
			classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
					"org.eclipse.jetty.annotations.AnnotationConfiguration");

			// Base URI for servlet context
			// URI baseUri = getWebRootResourceUri();
			// LOG.info("Base URI: " + baseUri);

			// Create Servlet context
			ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
			servletContextHandler.setContextPath("/");
			servletContextHandler.setResourceBase(WEBROOT_INDEX);

			// Since this is a ServletContextHandler we must manually configure JSP support.
			enableEmbeddedJspSupport(servletContextHandler);

			// Add Application Servlets
			// servletContextHandler.addServlet(DateServlet.class, "/date/");
			// Create Example of mapping jsp to path spec
			ServletHolder holderAltMapping = new ServletHolder();
			holderAltMapping.setName("foo.jsp");
			holderAltMapping.setForcedPath("/test/foo/foo.jsp");
			servletContextHandler.addServlet(holderAltMapping, "/test/foo/");

			// Default Servlet (always last, always named "default")
			ServletHolder holderDefault = new ServletHolder("default", DefaultServlet.class);
			holderDefault.setInitParameter("resourceBase", WEBROOT_INDEX);
			holderDefault.setInitParameter("dirAllowed", "true");
			servletContextHandler.addServlet(holderDefault, "/");
			server.setHandler(servletContextHandler);

			// Start Server
			server.start();

			// Show server state
			if (LOG.isLoggable(Level.FINE)) {
				LOG.fine(server.dump());
			}
		}

		/**
		 * Setup JSP Support for ServletContextHandlers.
		 * <p>
		 * NOTE: This is not required or appropriate if using a WebAppContext.
		 * </p>
		 *
		 * @param servletContextHandler
		 *            the ServletContextHandler to configure
		 * @throws IOException
		 *             if unable to configure
		 */
		private void enableEmbeddedJspSupport(ServletContextHandler servletContextHandler) throws IOException {
			// Establish Scratch directory for the servlet context (used by JSP compilation)
			File tempDir = new File(System.getProperty("java.io.tmpdir"));
			File scratchDir = new File(tempDir.toString(), "embedded-jetty-jsp");

			if (!scratchDir.exists()) {
				if (!scratchDir.mkdirs()) {
					throw new IOException("Unable to create scratch directory: " + scratchDir);
				}
			}
			servletContextHandler.setAttribute("javax.servlet.context.tempdir", scratchDir);

			// Set Classloader of Context to be sane (needed for JSTL)
			// JSP requires a non-System classloader, this simply wraps the
			// embedded System classloader in a way that makes it suitable
			// for JSP to use
			ClassLoader jspClassLoader = new URLClassLoader(new URL[0], this.getClass().getClassLoader());
			servletContextHandler.setClassLoader(jspClassLoader);

			// Manually call JettyJasperInitializer on context startup
			servletContextHandler.addBean(new JspStarter(servletContextHandler));

			// Create / Register JSP Servlet (must be named "jsp" per spec)
			ServletHolder holderJsp = new ServletHolder("jsp", JettyJspServlet.class);
			holderJsp.setInitOrder(0);
			holderJsp.setInitParameter("logVerbosityLevel", "DEBUG");
			holderJsp.setInitParameter("fork", "false");
			holderJsp.setInitParameter("xpoweredBy", "false");
			holderJsp.setInitParameter("compilerTargetVM", "1.8");
			holderJsp.setInitParameter("compilerSourceVM", "1.8");
			holderJsp.setInitParameter("keepgenerated", "true");
			servletContextHandler.addServlet(holderJsp, "*.jsp");
		}

		private URI getWebRootResourceUri() throws FileNotFoundException, URISyntaxException {
			// URL indexUri = this.getClass().getResource(WEBROOT_INDEX);
			URL indexUri = null;
			try {
				indexUri = new URL(WEBROOT_INDEX);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (indexUri == null) {
				throw new FileNotFoundException("Unable to find resource " + WEBROOT_INDEX);
			}
			// Points to wherever /webroot/ (the resource) is
			return indexUri.toURI();
		}

		public void stop() throws Exception {
			server.stop();
		}

		/**
		 * Cause server to keep running until it receives a Interrupt.
		 * <p>
		 * Interrupt Signal, or SIGINT (Unix Signal), is typically seen as a result of a
		 * kill -TERM {pid} or Ctrl+C
		 * 
		 * @throws InterruptedException
		 *             if interrupted
		 */
		public void waitForInterrupt() throws InterruptedException {
			server.join();
		}

}
