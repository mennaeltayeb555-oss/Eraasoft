package org.example.task7.serviceimp;

import org.example.task7.model.Player;
import org.example.task7.repository.PlayerRepository;
import org.example.task7.service.PlayerService;

import lombok.RequiredArgsConstructor;
import org.example.task7.exeption.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    // save player
    @Override
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    // update player
    @Override
    public Player updatePlayer(Player player) {
        if (!playerRepository.existsById(player.getId())) {
            throw new ResourceNotFoundException("Player not found with id: " + player.getId());
        }
        return playerRepository.save(player);
    }

    // get player by id
    @Override
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + id));
    }

    // delete player
    @Override
    public void deletePlayerById(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Player not found with id: " + id);
        }
        playerRepository.deleteById(id);
    }
}