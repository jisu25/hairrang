package hairrang.chart;

import hairrang.dto.Event;
import hairrang.dto.Guest;
import hairrang.dto.Hair;
import hairrang.dto.Sales;

public class HairrangDto {
	private Sales sales;
	private Guest guest;
	private Hair hair;
	private Event event;
	public HairrangDto() {
		// TODO Auto-generated constructor stub
	} 
	public HairrangDto(Sales sales, Guest guest, Hair hair, Event event) {
		super();
		this.sales = sales;
		this.guest = guest;
		this.hair = hair;
		this.event = event;
	}
	public Sales getSales() {
		return sales;
	}
	public void setSales(Sales sales) {
		this.sales = sales;
	}
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	public Hair getHair() {
		return hair;
	}
	public void setHair(Hair hair) {
		this.hair = hair;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	@Override
	public String toString() {
		return "HairrangDto [sales=" + sales + ", guest=" + guest + ", hair=" + hair + ", event=" + event + "]";
	}
	
}