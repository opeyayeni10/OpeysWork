package com.ericsson.NetworkManager.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name="event_cause")
public class EventCause {
	
	@EmbeddedId
    private EventCausePK eventCausePKId;
	
	@Column(name="description")
	private String description;

	public EventCausePK getId() {
		return eventCausePKId;
	}

	public void setId(final EventCausePK eventCausePKId) {
		this.eventCausePKId = eventCausePKId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

}
