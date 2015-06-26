package controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import service.FormatService;
import model.Format;

@Path("/format")
@Produces("application/json")
public class FormatController {
	FormatService formatService = FormatService.getInstance();

	@GET public List<Format> getFormats() {
		List<Format> formats = formatService.selectAll();
		return formats;
	}

	@POST public Format newFormat(Format format) {
		formatService.insert(format);
		return format;
	}
}
