package org.example.task7.controller;
import org.example.task7.service.PlayerService;
import org.example.task7.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    // save player
    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return playerService.savePlayer(player);
    }

    // update player
    @PutMapping
    public Player updatePlayer(@RequestBody Player player) {
        return playerService.updatePlayer(player);
    }

    // get player by id
    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

    // delete player
    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.deletePlayerById(id);
    }
}
