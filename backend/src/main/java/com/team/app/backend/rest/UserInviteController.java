package com.team.app.backend.rest;

import com.team.app.backend.persistance.model.UserInvite;
import com.team.app.backend.service.UserInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/user/invite")
public class UserInviteController {
    @Autowired
    UserInviteService userInviteService;

    @PostMapping("/send")
    public ResponseEntity sendUserInvite(@RequestBody UserInvite userInvite) {
        Map<String, String> response = new HashMap<>();

        try {
            userInviteService.sendUserInvite(userInvite);
        } catch (DataAccessException sqlEx){
            System.out.println(sqlEx);
            response.put("message", "There is a problem while creating user invite");
            ResponseEntity.badRequest().body(response);
        }
        response.put("message", "Invite was sent!");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List<UserInvite>> getUserInvite(@PathVariable("user_id") long id) {
        return ResponseEntity.ok().body(userInviteService.getUserInvite(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity acceptUserInvite(@PathVariable("id") long id) {
        Map<String, String> model = new HashMap<>();
        try {
            userInviteService.acceptUserInvite(id);
        }
        catch (DataAccessException sqlEx){
            System.out.println(sqlEx);
            model.put("message", "There is a problem while updating invite");
            return ResponseEntity.badRequest().body(model);
        }
        model.put("message", "Updated");
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity declineUserInvite(@PathVariable("id") long id) {
        Map<String, String> model = new HashMap<>();
        try {
            userInviteService.declineUserInvite(id);
        }
        catch (DataAccessException sqlEx) {
            System.out.println(sqlEx);
            model.put("message", "There is a problem while declining invite");
            return ResponseEntity.badRequest().body(model);
        }
        model.put("message", "Declined");
        return ResponseEntity.ok(model);
    }

    @GetMapping("/friends/{user_id}")
    public ResponseEntity<List<UserInvite>> getFriends(@PathVariable("user_id") long id) {
        return ResponseEntity.ok().body(userInviteService.getFriendsList(id));
    }

    @DeleteMapping("/friends/{user_id}/{delete_id}")
    public ResponseEntity deleteUserFromList(@PathVariable("user_id") long id,
                                             @PathVariable("delete_id") long deleteId) {
        Map<String, String> model = new HashMap<>();
        try {
            userInviteService.deleteUserFromList(id, deleteId);
        }
        catch (DataAccessException sqlEx) {
            System.out.println(sqlEx);
            model.put("message", "There is a problem while delete friend");
            return ResponseEntity.badRequest().body(model);
        }
        model.put("message", "Deleted");
        return ResponseEntity.ok(model);
    }
}
