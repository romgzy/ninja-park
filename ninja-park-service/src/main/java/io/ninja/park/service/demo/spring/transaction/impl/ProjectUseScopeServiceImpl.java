/**
 * 
 */
package io.ninja.park.service.demo.spring.transaction.impl;

import org.springframework.stereotype.Service;

import io.ninja.park.service.demo.spring.transaction.ProjectUseScopeService;
import java.util.List;

import io.ninja.park.model.ProjectUseScope;



/**
 * @author 
 *
 */
@Service
public class ProjectUseScopeServiceImpl implements ProjectUseScopeService {

	@Override
	public int save() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProjectUseScope> query(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectUseScope> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
