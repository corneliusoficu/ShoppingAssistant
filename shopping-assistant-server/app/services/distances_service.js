const geolib = require('geolib');

const Store = require('../models/store');
const StoreItems = require('../models/store_items');

Store.hasMany(StoreItems, {foreignKey: 'store_id'});
StoreItems.belongsTo(Store, {foreignKey: 'store_id'})


function filterStoresNearUser(stores, userData){
    return new Promise((resolve, reject)=>{
        var selectedStoresIds = [];
        for(var index = 0, len = stores.length; index < len; index++){
            var store = stores[index];
            var storeCoordinate = {latitude: store.lat, longitude: store.long};
            var userCoordinate = {latitude: userData.latitude, longitude: userData.longitude};
            var userDistanceToStore = geolib.getDistance(storeCoordinate, userCoordinate);

            if(userDistanceToStore < 800){
                console.log(userDistanceToStore, store.store_id);
                selectedStoresIds.push({itemId: store.store_id, distance: userDistanceToStore});
            }
        }
        resolve(selectedStoresIds);
    });
}

function addDistancesToItems(storeData, itemsData){
    // console.log(JSON.stringify(itemsData));
    return new Promise((resolve, reject)=>{
        for(var index = 0, len = itemsData.length; index < len; index++){
            var storeId = itemsData[index].store.store_id;
            for(var indexStoreData = 0, lenStoreData = storeData.length; indexStoreData < lenStoreData; indexStoreData++){
                if(storeData[indexStoreData].itemId == storeId){
                    itemsData[index].store["distance"] = storeData[indexStoreData].distance;
                }
            }
        }
        resolve(itemsData);
    });
}

function filterStoresThatHaveUsersDesiredItems(storeData, userData){
    var idsToSearch = storeData.map(x=>x.itemId);
    return new Promise((resolve, reject) => {
        StoreItems.findAll(
            {
                include: [Store],
                where:  {
                    item_id: userData.items_ids,
                    store_id: idsToSearch
                }

            }
        ).then(items=>{
            var itemsPlain = items.map((node) => node.get({ plain: true }));
            return addDistancesToItems(storeData, itemsPlain); 
        }).then(items=>{
            resolve(items);
        })
        
    })
    
}

exports.findStoresNearUser = function(userData, cb){
    Store.findAll()
    .then((stores) => {
        return filterStoresNearUser(stores, userData);
    })
    .then(nearStoresIds => {
        return filterStoresThatHaveUsersDesiredItems(nearStoresIds, userData);
    })
    .then(stores => {
        cb(stores);
    });
    
}

