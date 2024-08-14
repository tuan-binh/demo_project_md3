package com.ra.demo_project_md3.repository;

import com.ra.demo_project_md3.constants.RoleName;
import com.ra.demo_project_md3.model.Roles;

public interface IRoleDao
{
	Roles findByRoleName(RoleName roleName);
}
