package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.constants.GameEnum;

import java.awt.*;

class WordSearchLink extends GameLink {

    WordSearchLink(Component context) {
        super(context);
        this.gameEnum = GameEnum.WORD_SEARCH;
    }
}
