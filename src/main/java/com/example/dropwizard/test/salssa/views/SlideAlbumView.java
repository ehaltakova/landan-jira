package com.example.dropwizard.test.salssa.views;

import com.example.dropwizard.test.salssa.api.SlideAlbum;

import io.dropwizard.views.View;

public class SlideAlbumView extends View{

	private final SlideAlbum slideAlbum;
	
	public SlideAlbumView(SlideAlbum slideAlbum) {
		super("mustache/slidealbum.mustache");
		this.slideAlbum = slideAlbum;
	}
	
	public SlideAlbum getSlideAlbum() {
		return slideAlbum;
	}
}
