package org.example.task7.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    // save player
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    // update player
    public Player updatePlayer(Player player) {
        return playerRepository.save(player);
    }

    // get player by id
    public Player getPlayerById(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.orElse(null);
    }

    // delete player
    public void deletePlayerById(Long id) {
        playerRepository.deleteById(id);
    }
}