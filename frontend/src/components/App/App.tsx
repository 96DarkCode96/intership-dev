import {useFetchData} from '../../hooks/useFetchData';
import {DataTable} from '../DataTable/DataTable';
import {Sidebar} from '../Sidebar/Sidebar';
import './App.css';
import {useState} from "react";

function App() {
    const [filter, setFilter] = useState({});
    const {data} = useFetchData();

    return (
        <div className="app-container">
            <Sidebar/>
            <div className="main-container">
                <DataTable leaderboard={data} filter={filter} setFilter={setFilter}/>
            </div>
        </div>
    );
}

export default App;
