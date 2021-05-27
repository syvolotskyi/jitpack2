class MarketFlavor implements FlavorConfig {

    MarketFlavor() {

    }

    @Override
    Set<String> getNames() {
        return [GOOGLE, HUAWEI]
    }

    @Override
    String getDimension() {
        return DIMENSION
    }

    static String DIMENSION = 'market'
    static String GOOGLE = 'google'
    static String HUAWEI = 'huawei'

}
