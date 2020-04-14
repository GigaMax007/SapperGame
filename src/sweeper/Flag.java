package sweeper;

class Flag {
    private Matrix flagMap;


    void start() {
        flagMap = new Matrix(Box.CLOSED);
//        for (Coord around :                             // код для проверки
//                Ranges.getCoordsAround(new Coord(4, 4))
//        ) {
//            flagMap.set(around, Box.OPENED);
//        }
    }

    Box get(Coord coord) {
        return flagMap.get(coord);
    }

    public void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
    }

    public void toggleFlagedToBox(Coord coord) {
        switch (flagMap.get(coord)) {
            case FLAGED : setClosedToBox(coord); break;
            case CLOSED : setFlagedToBox(coord); break;
        }
    }

    private void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
    }

    public void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
    }

}
