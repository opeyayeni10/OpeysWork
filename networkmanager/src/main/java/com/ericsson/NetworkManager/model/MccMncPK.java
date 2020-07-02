package com.ericsson.NetworkManager.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MccMncPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int mcc;

	private int mnc;

	public MccMncPK() {
	}

	public MccMncPK(final int mcc, final int mnc) {
		super();
		this.mcc = mcc;
		this.mnc = mnc;
	}

	public int getMcc() {
		return mcc;
	}

	public void setMcc(final int mcc) {
		this.mcc = mcc;
	}

	public int getMnc() {
		return mnc;
	}

	public void setMnc(final int mNC) {
		mnc = mNC;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getMcc(), getMnc());
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MccMncPK)) {
			return false;
		}
		final MccMncPK that = (MccMncPK) obj;
		return Objects.equals(getMcc(), that.getMcc()) && Objects.equals(getMnc(), that.getMnc());
	}
}
