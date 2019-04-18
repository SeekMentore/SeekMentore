package geneticAlgorithm.imporvised.v1.model;

public class SelectedIndividualPool<T> implements Cloneable {
	
	private Integer totalCandidatesSelected;
	private Individual<T>[] selectedCandidatesInDescendingOrderOfFitnessFactor;
	private Integer[] selectedCandidatesIndexInDescendingOrderOfFitnessFactor;
	private Individual<T> bestCandidate;
	private Integer bestCandidateIndex;
	private Individual<T> secondBestCandidate;
	private Integer secondBestCandidateIndex;
	private Individual<T> worstCandidate;
	private Integer worstCandidateIndex;
	
	public SelectedIndividualPool() {}
	
	public Individual<T> getBestCandidate() {
		return bestCandidate;
	}

	public void setBestCandidate(Individual<T> bestCandidate) {
		this.bestCandidate = bestCandidate;
	}

	public Integer getBestCandidateIndex() {
		return bestCandidateIndex;
	}

	public void setBestCandidateIndex(Integer bestCandidateIndex) {
		this.bestCandidateIndex = bestCandidateIndex;
	}

	public Individual<T> getSecondBestCandidate() {
		return secondBestCandidate;
	}

	public void setSecondBestCandidate(Individual<T> secondBestCandidate) {
		this.secondBestCandidate = secondBestCandidate;
	}

	public Integer getSecondBestCandidateIndex() {
		return secondBestCandidateIndex;
	}

	public void setSecondBestCandidateIndex(Integer secondBestCandidateIndex) {
		this.secondBestCandidateIndex = secondBestCandidateIndex;
	}

	public Individual<T> getWorstCandidate() {
		return worstCandidate;
	}

	public void setWorstCandidate(Individual<T> worstCandidate) {
		this.worstCandidate = worstCandidate;
	}

	public Integer getTotalCandidatesSelected() {
		return totalCandidatesSelected;
	}
	
	public void setTotalCandidatesSelected(Integer totalCandidatesSelected) {
		this.totalCandidatesSelected = totalCandidatesSelected;
	}
	
	public Integer getWorstCandidateIndex() {
		return worstCandidateIndex;
	}
	
	public void setWorstCandidateIndex(Integer worstCandidateIndex) {
		this.worstCandidateIndex = worstCandidateIndex;
	}
	
	public Individual<T>[] getSelectedCandidatesInDescendingOrderOfFitnessFactor() {
		return selectedCandidatesInDescendingOrderOfFitnessFactor;
	}

	public void setSelectedCandidatesInDescendingOrderOfFitnessFactor(
			Individual<T>[] selectedCandidatesInDescendingOrderOfFitnessFactor) {
		this.selectedCandidatesInDescendingOrderOfFitnessFactor = selectedCandidatesInDescendingOrderOfFitnessFactor;
	}

	public Integer[] getSelectedCandidatesIndexInDescendingOrderOfFitnessFactor() {
		return selectedCandidatesIndexInDescendingOrderOfFitnessFactor;
	}

	public void setSelectedCandidatesIndexInDescendingOrderOfFitnessFactor(
			Integer[] selectedCandidatesIndexInDescendingOrderOfFitnessFactor) {
		this.selectedCandidatesIndexInDescendingOrderOfFitnessFactor = selectedCandidatesIndexInDescendingOrderOfFitnessFactor;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SelectedIndividualPool<T> clone() throws CloneNotSupportedException {
		return (SelectedIndividualPool<T>)super.clone();
	}
}
