package io.renren.common.utils;

import io.renren.modules.sys.entity.SysUserEntity;

public class SessionInfo {
    private SysUserEntity user;

    public SysUserEntity getUser() {
        return user;
    }

    public void setUser(SysUserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return user.getUsername();
    }
}
