package vn.dnict.microservice.security.model;

public class TokenProperties {
    private long maxAgeSeconds;
    private String secret;
    private String header;

    public TokenProperties(long maxAgeSeconds, String secret, String header) {
        this.maxAgeSeconds = maxAgeSeconds;
        this.secret = secret;
        this.header = header;
    }

    public TokenProperties() {
    }

    public long getMaxAgeSeconds() {
        return maxAgeSeconds;
    }
    public void setMaxAgeSeconds(long maxAgeSeconds) {
        this.maxAgeSeconds = maxAgeSeconds;
    }

    public String getSecret() {
        return secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
}
