package edu.epam.text.handler;

import edu.epam.text.composite.TextComponent;

public abstract class AbstractHandler {

    private AbstractHandler successor;

    public AbstractHandler() {
    }

    public AbstractHandler(AbstractHandler successor) {
        this.successor = successor;
    }

    abstract public TextComponent handleRequest(String data);

    protected TextComponent chain(String data){
        return successor.handleRequest(data);
    }
}
