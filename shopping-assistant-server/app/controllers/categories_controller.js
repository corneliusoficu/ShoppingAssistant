const { matchedData }       = require('express-validator/filter');
const {validationResult}    = require('express-validator/check');

const Category = require('../models/category');

exports.create_item = function(req, res) {
    
    const errors = validationResult(req);

    console.log(req.body);
    if(!errors.isEmpty()) {
        return res.status(422).json({errors: errors.mapped()});
    }
    
    const user = matchedData(req);
    Category.create(user).then(user=>res.json(user));
};

exports.get_all = function(req, res) {
    Category.findAll()
    .then((users)=>{
        return res.status(200).json(users);
    })
    .catch((err)=>{
        return res.status(404);
    });
};