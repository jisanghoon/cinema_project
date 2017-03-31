package joon.temp;

import cinema.model.Auditorium;
import cinema.model.Movie;
import cinema.model.Schedule;
import cinema.model.Screen;
import cinema.model.Seat;
import cinema.model.Theater;

public class BookTotal {
	private Schedule schedule;
	private Seat seat;
	private Auditorium auditorium;
	private Theater theater;
	private Screen screen;
	private Movie movie;

	public BookTotal(Schedule schedule, Seat seat, Auditorium auditorium, Theater theater, Screen screen, Movie movie) {
		super();
		this.schedule = schedule;
		this.seat = seat;
		this.auditorium = auditorium;
		this.theater = theater;
		this.screen = screen;
		this.movie = movie;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public Seat getSeat() {
		return seat;
	}

	public Auditorium getAuditorium() {
		return auditorium;
	}

	public Theater getTheater() {
		return theater;
	}

	public Screen getScreen() {
		return screen;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public void setAuditorium(Auditorium auditorium) {
		this.auditorium = auditorium;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}
