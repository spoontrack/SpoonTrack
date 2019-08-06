package com.spoontrack.core;

import com.spoontrack.core.entity.Pub;
import com.spoontrack.core.io.RequestHandler;

import java.util.List;

public class SpoonTrack {
    private static List<Pub> pubs;

    public static void main(String[] args) {
        collectPubs();
    }

    private static void collectPubs() {
        pubs = new RequestHandler().getPubs();
    }
}
