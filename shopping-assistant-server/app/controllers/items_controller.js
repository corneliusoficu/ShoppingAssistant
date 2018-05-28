const Item = require('../models/item');

exports.get_all = function(req, res) {

    var categoryId = req.params.categoryId;

    if(categoryId == null) {
        return res.status(404);
    }

    Item.findAll({
        where: {
            category_id: categoryId
        }
    })
    .then((items)=>{
        return res.status(200).json(items);
    })
    .catch((err)=>{
        return res.status(404);
    });
}