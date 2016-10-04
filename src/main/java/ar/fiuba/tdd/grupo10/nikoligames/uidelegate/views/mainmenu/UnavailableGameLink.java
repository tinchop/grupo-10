package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu;

class UnavailableGameLink extends GameLink {

    @Override
    public void execute(GameEnum gameEnum) {
        throw new RuntimeException("Game not available: " + gameEnum);
    }

}
