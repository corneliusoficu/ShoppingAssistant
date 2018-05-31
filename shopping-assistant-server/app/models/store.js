const {check} = require('express-validator/check');

const Sequelize = require('sequelize');
const sequelize = require('../../config/db');

Store = sequelize.define('stores', {
    store_id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        allowNull: false,
        autoIncrement: true
    },
    store_name: {
        type: Sequelize.STRING,
        allowNull: false
    },
    lat: {
        type: Sequelize.DOUBLE,
        allowNull: false
    },
    long: {
        type: Sequelize.DOUBLE,
        allowNull: false
    }
},
{
    timestamps: false,
    freezeTableName: true,
});

module.exports = Store;