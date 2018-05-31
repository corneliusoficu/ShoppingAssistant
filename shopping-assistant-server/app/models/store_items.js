const {check} = require('express-validator/check');

const Sequelize = require('sequelize');
const sequelize = require('../../config/db');

Store_items = sequelize.define('store_items', {
    store_id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        allowNull: false,
    },
    item_id: {
        type: Sequelize.INTEGER,
        allowNull: false
    },
},
{
    timestamps: false,
    freezeTableName: true,
});

module.exports = Store_items;