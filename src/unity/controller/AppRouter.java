package unity.controller;

import org.slim3.controller.router.RouterImpl;

public class AppRouter extends RouterImpl {

    public AppRouter() {
       // addRouting("/_ah/mail/{address}", "/mail/receive?address={address}");
        addRouting("/user/{name}", "/user/?name={name}");
        addRouting("/unitygames/game/ug{id}", "/unitygames/game?id={id}");
        
        /*
         * addRouting( "/{app}/edit/{key}/{version}",
         * "/{app}/edit?key={key}&version={version}"); addRouting(
         * "/{app}/delete/{key}/{version}",
         * "/{app}/delete?key={key}&version={version}");
         * addRouting("/{app}/find/*path", "/{app}/find?path={path}");
         */
    }
}