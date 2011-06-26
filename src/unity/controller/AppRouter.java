package unity.controller;

import org.slim3.controller.router.RouterImpl;

public class AppRouter extends RouterImpl {

    public AppRouter() {
        addRouting("/user/{name}", "/user/?name={name}");
        addRouting("/unitygames/game/ug{id}", "/unitygames/game?id={id}");
        addRouting("/api/game/{gameId}", "/api/gameApi?id={gameId}");
        addRouting("/unitygames/upload/{newGame}", "/unitygames/upload/?g={newGame}");
        addRouting("/unitygames/upload/{newGame}/{guest}", "/unitygames/upload/?g={newGame}&t={guest}");
        addRouting("/unitygames/upload/{newGame}/{guest}{id}", "/unitygames/upload/?g={newGame}&t={guest}&id={id}");
    }
}