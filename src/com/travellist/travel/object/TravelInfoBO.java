package com.travellist.travel.object;

public class TravelInfoBO {
	String travelName;
	Integer travelNo;
	long id;
	String useYn;
	
	
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getTravelName() {
		return travelName;
	}
	public void setTravelName(String travelName) {
		this.travelName = travelName;
	}
	
	public Integer getTravelNo() {
		return travelNo;
	}
	public void setTravelNo(Integer travelNo) {
		this.travelNo = travelNo;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "TravelInfoBO [travelName=" + travelName + ", travelNo="
				+ travelNo + ", id=" + id + "]";
	}
}
