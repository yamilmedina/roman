package com.wire.bots.ealarming.resources;

import com.wire.bots.ealarming.DAO.GroupsDAO;
import com.wire.bots.ealarming.DAO.UserDAO;
import com.wire.bots.ealarming.model.Group;
import com.wire.bots.ealarming.model.SearchResult;
import com.wire.bots.sdk.server.model.ErrorMessage;
import com.wire.bots.sdk.tools.Logger;
import io.swagger.annotations.*;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api
@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {
    private final GroupsDAO groupsDAO;
    private final UserDAO userDAO;

    public SearchResource(DBI jdbi) {
        this.userDAO = jdbi.onDemand(UserDAO.class);
        this.groupsDAO = jdbi.onDemand(GroupsDAO.class);
    }

    @GET
    @ApiOperation(value = "Search users and groups by the keyword", response = SearchResult.class)
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Something went wrong")})
    public Response search(@ApiParam @QueryParam("q") String keyword,
                           @ApiParam(defaultValue = "10") @QueryParam("size") int size) {
        try {
            if (size == 0)
                size = 10;

            SearchResult result = new SearchResult();
            result.users = userDAO.search(keyword, size);
            result.groups = groupsDAO.search(keyword, size);
            for (Group group : result.groups) {
                group.size = groupsDAO.size(group.id);
            }
            return Response.
                    ok(result).
                    build();
        } catch (Exception e) {
            Logger.error("SearchResource.search?q=%s : %s", keyword, e);
            return Response
                    .ok(new ErrorMessage(e.getMessage()))
                    .status(500)
                    .build();
        }
    }
}