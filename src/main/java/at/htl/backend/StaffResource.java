package at.htl.backend;

import at.htl.backend.models.Staff;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Path("/staff")
public class StaffResource {
    private List<Staff> staffList = new ArrayList<>();{
        staffList.add(new Staff(1L, "Max", "Mustermann", "06601234567", "working"));
        staffList.add(new Staff(2L, "Lena", "Musterfrau", "06601234567", "sick"));
        staffList.add(new Staff(3L, "Linus", "Nestler", "06601234567", "availiable"));
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Staff> getAll() { return staffList; }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Staff getStaffById(@PathParam("id") Long id) {
        return staffList.stream().filter(s -> Objects.equals(s.getId(), id)).findFirst().orElse(null);
    }

    @PUT
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public void createStaff(Staff staff) {
        staffList.add(staff);
    }

    @PATCH
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") Long id, Staff staff) {
        staffList.stream().filter(s -> Objects.equals(s.getId(), id)).findFirst().ifPresent(s -> {
            s.setFirstName(staff.getFirstName());
            s.setLastName(staff.getLastName());
            s.setTelephone(staff.getTelephone());
            s.setStatus(staff.getStatus());
        });
    }
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteStaff(int id) {
        staffList.removeIf(s -> s.getId() == id);
    }
}
