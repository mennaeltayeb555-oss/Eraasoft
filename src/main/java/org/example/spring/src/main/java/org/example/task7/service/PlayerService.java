package org.example.task7.service;

import org.example.task7.model.Player;

public interface PlayerService {

    Player savePlayer(Player player);

    Player updatePlayer(Player player);

    Player getPlayerById(Long id);

    void deletePlayerById(Long id);
}
