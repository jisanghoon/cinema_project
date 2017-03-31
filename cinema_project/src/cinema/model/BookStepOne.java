package cinema.model;

import cinema.model.Auditorium;
import cinema.model.Movie;
import cinema.model.Schedule;
import cinema.model.Screen;
import cinema.model.Theater;

public class BookStepOne {
	private Schedule schedule;
	private Screen screen;
	private Movie movie;
	private Auditorium auditorium;
	private Theater theater;

	public BookStepOne(Schedule schedule, Screen screen, Movie movie, Auditorium auditorium, Theater theater) {
		super();
		this.schedule = schedule;
		this.screen = screen;
		this.movie = movie;
		this.auditorium = auditorium;
		this.theater = theater;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public Screen getScreen() {
		return screen;
	}

	public Movie getMovie() {
		return movie;
	}

	public Auditorium getAuditorium() {
		return auditorium;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public void setAuditorium(Auditorium auditorium) {
		this.auditorium = auditorium;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

}
