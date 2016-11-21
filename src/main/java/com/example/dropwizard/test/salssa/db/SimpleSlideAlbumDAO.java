package com.example.dropwizard.test.salssa.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.example.dropwizard.test.salssa.api.SimpleSlideAlbum;

@RegisterMapper(SlideAlbumSimpleMapper.class)
public interface SimpleSlideAlbumDAO {
	
	@SqlQuery("select * from slidealbum where title = :title and customer = :customer")
	SimpleSlideAlbum getSlideAlbum(@Bind("title") String title, @Bind("customer") String customer);

	@SqlQuery("select * from slidealbum where id = :id")
	SimpleSlideAlbum getSlideAlbumById(@Bind("id") long id);

	@SqlQuery("select * from slidealbum")
	List<SimpleSlideAlbum> getSlideAlbums();
}
