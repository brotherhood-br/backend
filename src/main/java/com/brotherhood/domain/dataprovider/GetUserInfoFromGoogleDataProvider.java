package com.brotherhood.domain.dataprovider;

import com.brotherhood.domain.model.UserSSO;

public interface GetUserInfoFromGoogleDataProvider {
    UserSSO getUserInfo(String ssoToken);
}
