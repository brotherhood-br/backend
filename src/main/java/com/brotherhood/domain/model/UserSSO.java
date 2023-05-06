package com.brotherhood.domain.model;

public class UserSSO {
    private String userId;
    private String email;
    private String pictureUrl;

    public UserSSO(String userId, String email, String pictureUrl) {
        this.userId = userId;
        this.email = email;
        this.pictureUrl = pictureUrl;
    }

    public UserSSO() {
    }

    public static UserSSOBuilder builder() {
        return new UserSSOBuilder();
    }

    public String getUserId() {
        return this.userId;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPictureUrl() {
        return this.pictureUrl;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserSSO)) return false;
        final UserSSO other = (UserSSO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$pictureUrl = this.getPictureUrl();
        final Object other$pictureUrl = other.getPictureUrl();
        if (this$pictureUrl == null ? other$pictureUrl != null : !this$pictureUrl.equals(other$pictureUrl))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserSSO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $pictureUrl = this.getPictureUrl();
        result = result * PRIME + ($pictureUrl == null ? 43 : $pictureUrl.hashCode());
        return result;
    }

    public String toString() {
        return "UserSSO(userId=" + this.getUserId() + ", email=" + this.getEmail() + ", pictureUrl=" + this.getPictureUrl() + ")";
    }

    public static class UserSSOBuilder {
        private String userId;
        private String email;
        private String pictureUrl;

        UserSSOBuilder() {
        }

        public UserSSOBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public UserSSOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserSSOBuilder pictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
            return this;
        }

        public UserSSO build() {
            return new UserSSO(userId, email, pictureUrl);
        }

        public String toString() {
            return "UserSSO.UserSSOBuilder(userId=" + this.userId + ", email=" + this.email + ", pictureUrl=" + this.pictureUrl + ")";
        }
    }
}
