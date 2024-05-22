package net.codejava.service;

import net.codejava.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DynamicPersissionServiceImpl {
    @Autowired
    PermissionRepository permissionRepository;
}
