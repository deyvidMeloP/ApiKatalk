package com.example.demo.ChatEntity;

public class CandidateEntity {
	
	private String type;
    private CandidateSub candidate;
    private CallEntity call;
    
    public CandidateEntity() {
    }

    // Construtor com parâmetros
    public CandidateEntity(String type, CandidateSub candidate, CallEntity call) {
        this.type = type;
        this.candidate = candidate;
        this.call = call;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CandidateSub getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateSub candidate) {
        this.candidate = candidate;
    }
    
	public CallEntity getCall() {
		return call;
	}

	public void setCall(CallEntity call) {
		this.call = call;
	}


}
