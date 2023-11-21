
class Product {

  String _itemName;
  String _itemDesc;
  double _itemPrice;
  int _itemStock;
  String _itemURL;

  //Setters And Getters
  String get itemName => _itemName;
  set itemName(String itemName) {
    _itemName = itemName;
  }

  String get url => _itemURL;

  String get itemDesc => _itemDesc;
  set itemDesc(String itemDesc) {
    _itemDesc = itemDesc;
  }

  double get itemPrice => _itemPrice;
  set itemPrice(double itemPrice) {
    _itemPrice = itemPrice;
  }

  int get itemStock => _itemStock;
  set itemStock(int itemStock) {
    _itemStock = itemStock;
  }

  void decrementStock(String itemName){
    if(_itemStock>=1) _itemStock--;
  }

  //Constructors
  Product(this._itemName, this._itemDesc, this._itemPrice, this._itemStock, this._itemURL);

  factory Product.fromJSON(Map<String,dynamic> data){
    return Product(data['title'],
        data['description'],
        data['price'],
        data['stock'],
        data['thumbnail']);
  }


  static final List<Product> productList =[
    Product('Clay Mask', 'It is a chemical exfoliator for the face to rejuvenate.', 80, 50, 'https://ph-test-11.slatic.net/p/70eb27d50c37254f4cdbf40a0160bc68.jpg'),
    Product('Lotions', 'Used for moisturizing the skin.', 100, 80, 'https://www.aveeno.com.ph/sites/aveeno_ph_2/files/styles/product_image/public/product-images/aveeno-daily-moisturizing-lotion-354ml.jpg'),
    Product('Jump Ropes', 'A great use for exercises to improve endurance.', 100, 30, 'https://pyxis.nymag.com/v1/imgs/920/e12/99a414943d94f12cc74426189a4938e92e.2x.rsquare.w600.jpg'),
    Product('Dumbbells', 'Used for Workouts and exercises can be used in or outdoors', 450, 0, 'https://cdn.shopify.com/s/files/1/0564/1283/1950/collections/1W9A3573_1_1_1400x.jpg?v=1621515174'),
    Product('Hydro Flask', 'A water bottle that is designed to keep your water cold or warm for a long period of time', 50, 100, 'https://www.insidehook.com/wp-content/uploads/2021/04/hydroflask1.jpg?fit=1500%2C1000'),
    Product('Yoga mats', 'Can be used at home outside and even anywhere.', 45, 150, 'https://www.sportsmnl.com/wp-content/uploads/2020/10/6714-1-450x450-1.jpg'),
    Product('Phone cases', 'Designed to protect your phone but also follows a trend of designs.', 20, 500, 'https://lzd-img-global.slatic.net/g/p/e00de8fcf6b6d92d27e3288c5402b927.jpg_2200x2200q80.jpg_.webp'),
    Product('T-shirts', 'Has many sizes  S, M, L, XL and comes with different colors', 10, 250, 'https://assets.vogue.com/photos/61b8faa39f520c634b44641a/3:4/w_1280%2Cc_limit/slide_6.jpg'),
    Product('Pants', 'Fitted and thin use of fabric',15, 250, 'https://storage.sg.content-cdn.io/cdn-cgi/image/width=542,height=723,quality=75,format=auto,fit=cover,g=top/in-resources/21e9ae3c-de72-4391-9c4a-c7af58447630/Images/ProductImages/Source/levis-mens-xx-chino-ezwaist-taper-pants-A10410035_01_Front.jpg'),
    Product('Totes and bags','Use to reduce the consumption of plastic bags.', 5, 600, 'https://media.gq.com/photos/5ceeea351f8a4ee9adddadc4/16:9/w_2560%2Cc_limit/TotesLede3.jpg'),
    Product('Mugs', 'Designed to hold hot drinks like coffee, hot chocolate and ect.', 4, 1000,'https://images.summitmedia-digital.com/yummyph/images/2020/08/14/homescapes2.jpg'),
    Product('Mouse pad', 'Designed for a gamers need it has control while also maintaining its glide.', 20, 700, 'https://rexus.id/wp-content/uploads/2020/12/mousepad-daxa2-600x364.jpeg'),
    Product('Keyboard', 'It is a loud 70 percent keyboard with optical brown switches', 35, 150, 'https://www.cnet.com/a/img/resize/c2fb79b15d18f335e85fc4acd72910059cc9758b/hub/2021/08/20/453e37bf-61cb-4e16-ad90-fd822bdc390a/keychron-k3-mechanical-keyboard.jpg?auto=webp&fit=crop&height=900&width=1200'),
    Product('Headset', 'Designed for a gamers needs has 7.1 surround sound while also having noise cancellation', 40, 1000, 'https://images-cdn.ubuy.co.id/3SX865Y-gaming-headset-xbox-one-headset-with.jpg'),
    Product('PS4 Controller', 'Cheap and original Controllers for the PS4 console', 70, 40, 'https://i5.walmartimages.com/asr/d1697537-8da7-4a29-b37d-2c90bcf2e6fb_2.f614853ee3df4d31cf2f5ea0130e7546.jpeg'),
  ];
}