package com.ericsson.NetworkManager.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EventCausePK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "cause_code")
	private String causeCode;

	@Column(name = "event_id")
	private int eventID;

	public EventCausePK() {

	}

	public EventCausePK(final String causeCode, final int eventID) {
		this.causeCode = causeCode;
		this.eventID = eventID;
	}

	public String getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(final String causeCode) {
		this.causeCode = causeCode;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(final int eventID) {
		this.eventID = eventID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCauseCode(), getEventID());
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof EventCausePK)) {
			return false;
		}
		final EventCausePK that = (EventCausePK) obj;
		return Objects.equals(getCauseCode(), that.getCauseCode()) && Objects.equals(getEventID(), that.getEventID());
	}

}
