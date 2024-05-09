//Se lee de la BD
const initProducts = [
    {
        id:1,
        name: "Monitos Samsung 64",
        price: 500.00,
        description: "Monitor 4k"
    },
    {
        id:2,
        name: "Apple iPhone 15",
        price: 500.00,
        description: "Apple"
    }
];

export const listProduct = () =>{
    return initProducts
}