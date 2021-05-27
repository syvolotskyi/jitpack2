class CountryFlavor implements FlavorConfig {

    private Set<String> list

    CountryFlavor(List<Set> countries = [GEORGIA, UZBEKISTAN]) {
        list = countries.toSet()
    }

    @Override
    Set<String> getNames() {
        return list
    }

    @Override
    String getDimension() {
        return DIMENSION
    }

    static String DIMENSION = 'country'
    static String GEORGIA = 'georgia'
    static String UZBEKISTAN = 'uzbekistan'

}
