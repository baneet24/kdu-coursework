const express = require("express");
const priceList = require("../../data/priceList");
const router = express.Router();
const crypto = require("crypto")

router.get('/', (req, res) => {
   const generatedprice = crypto.randomInt(0, 500)
   priceList.push(generatedprice);
   res.send(generatedprice);  

})


router.post('/', (req, res) => {
    try {
        boughtlist.push({
          price: req.body.price,
          quantity: req.body.quantity,
          type: req.body.type,
          qtime: req.body.qtime
        });
    
        res.status(201).send("Coin bought successfully")
}
catch (error){
    res.statusCode(500).send(error)
}
})

module.exports = router;
