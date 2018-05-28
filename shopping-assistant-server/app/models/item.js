const {check} = require('express-validator/check');

const Sequelize = require('sequelize');
const sequelize = require('../../config/db');

Item = sequelize.define('items', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        allowNull: false,
        autoIncrement: true
    },
    item_name: {
        type: Sequelize.STRING,
        allowNull: false
    },
    category_id: {
        type: Sequelize.INTEGER,
        allowNull: false,
    }
},
{
    timestamps: false,
    freezeTableName: true,
});

module.exports = Item;