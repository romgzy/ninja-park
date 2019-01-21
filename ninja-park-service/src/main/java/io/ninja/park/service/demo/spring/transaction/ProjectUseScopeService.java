/**
 * 
 */
package io.ninja.park.service.demo.spring.transaction;

import java.util.List;

import io.ninja.park.model.ProjectUseScope;

/**
 * @author 
 *
 */
public interface ProjectUseScopeService {
	int save();

	List<ProjectUseScope> query(Long id);

	List<ProjectUseScope> queryAll();
}
