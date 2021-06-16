const a=[
    {time:1,path:'asd'},
    {time:4,path:'assad'},
    {time:23,path:'2wasd'},
]

let b=a.map((i)=>i.time)
b.shift()
console.log(b)