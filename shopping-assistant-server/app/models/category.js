const {check} = require('express-validator/check');

const Sequelize = require('sequelize');
const sequelize = require('../../config/db');

Category = sequelize.define('category', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        allowNull: false,
        autoIncrement: true
    },
    category_name: {
        type: Sequelize.STRING,
        allowNull: false
    },
    image_link: {
        type: Sequelize.STRING,
        allowNull: true
    }
},
{
    timestamps: false,
    freezeTableName: true,
});

module.exports = Category;
