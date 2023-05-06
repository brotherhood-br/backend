package com.brotherhood.configuration;

import io.micronaut.context.annotation.Value;

public class MsEnvironment {
    @Value("${google.clientId}")
    private String googleClientId;

    public MsEnvironment() {
    }

    public String getGoogleClientId() {
        return this.googleClientId;
    }

    public void setGoogleClientId(String googleClientId) {
        this.googleClientId = googleClientId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MsEnvironment)) return false;
        final MsEnvironment other = (MsEnvironment) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$googleClientId = this.getGoogleClientId();
        final Object other$googleClientId = other.getGoogleClientId();
        if (this$googleClientId == null ? other$googleClientId != null : !this$googleClientId.equals(other$googleClientId))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MsEnvironment;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $googleClientId = this.getGoogleClientId();
        result = result * PRIME + ($googleClientId == null ? 43 : $googleClientId.hashCode());
        return result;
    }

    public String toString() {
        return "MsEnvironment(googleClientId=" + this.getGoogleClientId() + ")";
    }
}
