package cinema.model;

import java.util.List;

public class ScreenViewDataForJson {

	// AjaxReqHandler 에서 사용..
	List<Auditorium> auditoriumList;
	List<Screen> screenList;

	public List<Auditorium> getAuditoriumList() {
		return auditoriumList;
	}

	public void setAuditoriumList(List<Auditorium> auditoriumList) {
		this.auditoriumList = auditoriumList;
	}

	public List<Screen> getScreenList() {
		return screenList;
	}

	public void setScreenList(List<Screen> screenList) {
		this.screenList = screenList;
	}

}
