const {check}   = require('express-validator/check');
const Category  = require('../models/category');

rules = [
    check('category_name')
        .exists().withMessage("Category must be provided!")
        .matches(/^[A-Z].*/,'g').withMessage("Category name must start with an upper letter!") //Starts with uppercase
        .custom(value => {
            return Category
                .findOne({where: {category_name: value}})
                .then(token => token === null)
        }).withMessage("Category already exists!")
        
]

module.exports = rules;
