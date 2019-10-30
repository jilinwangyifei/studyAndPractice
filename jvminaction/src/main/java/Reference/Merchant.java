package Reference;


class Merchant<T extends Customer> {
    public double actionPrice(T customer) {
        return 0.0d;
    }
}

class VIPOnlyMerchant extends Merchant<VIP> {
    @Override
    public double actionPrice(VIP customer) {
        return 0.0d;
    }
}


class Customer {

}

class VIP extends Customer {

}