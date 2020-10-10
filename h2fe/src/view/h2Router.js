import Header from "../components/HelloWorld.vue";
import path from "../util/path";
import HelloWorld from "../view/HelloWorld.vue";



const h2f_Routes = [
    {
        path: path.HOME,
        name: "HOME",
        children:[
            {
                path: path.HELLO,
                component: HelloWorld,
                name: "HelloWorld"
            },
            {
                path: path.HEADER,
                component: Header,
                name: "Header",
                props: true
            }

        ]
    }
];
export default h2f_Routes;
