package bg.nemetschek.landan.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import bg.nemetschek.landan.api.SimpleSlideAlbum;

public class SlideAlbumSimpleMapper implements ResultSetMapper<SimpleSlideAlbum> {
  
	public SimpleSlideAlbum map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    
	  return new SimpleSlideAlbum.Builder(r.getString("title"), r.getString("customer"))
    		.modificationDate(r.getLong("modificationDate"))
    		.svg(r.getString("svg"))
    		.lockedBy(r.getString("locked"))
    		.build();
  }
}