import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { StockListPage } from './pages/StockListPage';
import { SummarizerPage } from './pages/SummarizerPage';
import { PortfolioPage } from './pages/PortfolioPage';
import { Watchlist } from './pages/WatchListPage';
import { StockDetailPage } from './pages/StockDetailPage';
import { DrawerAppBar } from './components/ Navbar';


function App()  {
  return (
    <BrowserRouter>
    <DrawerAppBar/> 
    <Routes>
        <Route path="/" element={<StockListPage/>} />
        <Route path="/Summarizer" element={<SummarizerPage/>} />
        <Route path="/My Portfolio" element={<PortfolioPage/>} />
        <Route path = "/watchlist" element={<Watchlist/>}/>
        <Route path = '/stockDetailPage/:name' element = {<StockDetailPage/>} />
    </Routes>
    </BrowserRouter>
  );
}

export default App;